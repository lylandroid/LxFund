package com.kscf.app.android.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.framework.util.L;
import com.kscf.app.android.app.App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

public class FileUtils {

    public static final String ROOT_DIR = String.valueOf(Math.abs("lylAndroid_cache".hashCode()));
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String WEB_CACHE_DIR = "webcache";
    public static final String ICON_DIR = "icon";
    public static final String DATABASE_DIR = "database";

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载目录
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 获取数据库目录
     */
    public static String getDatabaseDir() {
        return getDir(DATABASE_DIR);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    public static String getAssetDir() {
        return "file:///android_asset/";
    }

    /**
     * 获取webview缓存目录
     */
    public static String getWebViewCacheDir() {
        return getDir(WEB_CACHE_DIR);
    }

    /**
     * 获取icon目录
     */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = App.getInstance().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 判断文件是否可写
     */
    public static boolean isWriteable(String path) {
        try {
            if (TextUtils.isEmpty(path)) {
                return false;
            }
            File f = new File(path);
            return f.exists() && f.canWrite();
        } catch (Exception e) {
            L.e(e);
            return false;
        }
    }

    /**
     * 修改文件的权限,例如"777"等
     */
    public static void chmod(String path, String mode) {
        try {
            String command = "chmod " + mode + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (Exception e) {
            L.e(e);
        }
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getExternalStoragePath())) {
            filePath = getExternalStoragePath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardAvailable()) {
            StatFs stat = new StatFs(getExternalStoragePath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 把Stream转换成String
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 从路径获取文件名
     *
     * @param pathAndName
     * @return
     */
    public static String getFileName(String pathAndName) {
        int start = pathAndName.lastIndexOf("/");
        int end = pathAndName.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathAndName.substring(start + 1, end);
        } else {
            return null;
        }
    }

    /**
     * 遍历文件夹中文件
     *
     * @param filepath 文件路径
     * @return 返回file［］ 数组
     */
    public static File[] getFileList(String filepath) {
        File d = null;
        File list[] = null;
        /** 建立当前目录中文件的File对象 **/
        try {
            d = new File(filepath);
            if (d.exists()) {
                list = d.listFiles();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** 取得代表目录中所有文件的File对象数组 **/
        return list;
    }

    /**
     * 读取文本文件内容
     *
     * @param filePathAndName 带有完整绝对路径的文件名
     * @param encoding        文本文件打开的编码方式
     * @return 返回文本文件的内容
     */
    public static String readTxt(String filePathAndName, String encoding) throws IOException {
        encoding = encoding.trim();
        StringBuffer str = new StringBuffer("");
        String st = "";
        try {
            FileInputStream fs = new FileInputStream(filePathAndName);
            InputStreamReader isr;
            if (encoding.equals("")) {
                isr = new InputStreamReader(fs);
            } else {
                isr = new InputStreamReader(fs, encoding);
            }
            BufferedReader br = new BufferedReader(isr);
            try {
                String data = "";
                while ((data = br.readLine()) != null) {
                    str.append(data);
                }
            } catch (Exception e) {
                str.append(e.toString());
            }
            st = str.toString();
            if (st != null && st.length() > 1)
                st = st.substring(0, st.length() - 1);
        } catch (IOException es) {
            st = "";
        }
        return st;
    }

    /**
     * 新建目录
     *
     * @param folderPath 目录
     * @return 返回目录创建后的路径
     */
    public static String createFolder(String folderPath) {
        String txt = folderPath;
        try {
            File myFilePath = new File(txt);
            txt = folderPath;
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txt;
    }

    /**
     * 多级目录创建
     *
     * @param folderPath 准备要在本级目录下创建新目录的目录路径例如 c:myf
     * @param paths      无限级目录参数，各级目录以单数线区分 例如 a|b|c
     * @return 返回创建文件后的路径
     */
    public static String createFolders(String folderPath, String paths) {
        String txts = folderPath;
        try {
            String txt;
            txts = folderPath;
            StringTokenizer st = new StringTokenizer(paths, "|");
            for (int i = 0; st.hasMoreTokens(); i++) {
                txt = st.nextToken().trim();
                if (txts.lastIndexOf("/") != -1) {
                    txts = createFolder(txts + txt);
                } else {
                    txts = createFolder(txts + txt + "/");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return txts;
    }

    /**
     * 新建文件
     *
     * @param filePathAndName 文本文件完整绝对路径及文件名
     * @param fileContent     文本文件内容
     * @return
     */
    public static void createFile(String filePathAndName, String fileContent) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.close();
            resultFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 有编码方式的文件创建
     *
     * @param filePathAndName 文本文件完整绝对路径及文件名
     * @param fileContent     文本文件内容
     * @param encoding        编码方式 例如 GBK 或者 UTF-8
     * @return
     */
    public static void createFile(String filePathAndName, String fileContent, String encoding) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            PrintWriter myFile = new PrintWriter(myFilePath, encoding);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param filePathAndName 文本文件完整绝对路径及文件名
     * @return Boolean 成功删除返回true遭遇异常返回false
     */
    public static boolean delFile(String filePathAndName) {
        boolean bea = false;
        try {
            String filePath = filePathAndName;
            File myDelFile = new File(filePath);
            if (myDelFile.exists()) {
                myDelFile.delete();
                bea = true;
            } else {
                bea = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bea;
    }

    /**
     * 删除文件
     *
     * @param folderPath 文件夹完整绝对路径
     * @return
     */
    public static void delFolder(String folderPath) {
        try {
            /** 删除完里面所有内容 **/
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            /** 删除空文件夹 **/
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean bea = false;
        File file = new File(path);
        if (!file.exists()) {
            return bea;
        }
        if (!file.isDirectory()) {
            return bea;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                /** 先删除文件夹里面的文件 **/
                delAllFile(path + "/" + tempList[i]);
                /** 再删除空文件 **/
                delFolder(path + "/" + tempList[i]);
                bea = true;
            }
        }
        return bea;
    }

    /**
     * 复制单个文件
     *
     * @param oldPathFile 准备复制的文件源
     * @param newPathFile 拷贝到新绝对路径带文件名
     * @return
     */
    public static void copyFile(String oldPathFile, String newPathFile) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPathFile);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPathFile);
                FileOutputStream fs = new FileOutputStream(newPathFile);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制整个文件夹的内容
     *
     * @param oldPath 准备拷贝的目录
     * @param newPath 指定绝对路径的新目录
     * @return
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            /** 如果文件夹不存在 则建立新文件 **/
            new File(newPath).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                /** 如果是子文件 **/
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动文件
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动目录
     *
     * @param oldPath
     * @param newPath
     * @return
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
    }

    /**
     * 建立一个可以追加的BufferedReader
     *
     * @param fileDir
     * @param fileName
     * @return
     */
    public static BufferedWriter getWriter(String fileDir, String fileName) {
        try {
            File f1 = new File(fileDir);
            if (!f1.exists()) {
                f1.mkdirs();
            }
            f1 = new File(fileDir, fileName);
            if (!f1.exists()) {
                f1.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f1.getPath(), true));
            return bw;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 得到一个BufferedReader
     *
     * @param fileDir
     * @param fileName
     * @param encoding
     * @return
     */
    public static BufferedReader getReader(String fileDir, String fileName, String encoding) {
        try {
            File file = new File(fileDir, fileName);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader br = new BufferedReader(read);
            return br;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将异常堆栈转换为字符串
     *
     * @param aThrowable 异常
     * @return String
     */
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     * 把数据写入文件
     *
     * @param is       数据流
     * @param path     文件路径
     * @param recreate 如果文件存在，是否需要删除重建
     * @return 是否写入成功
     */
    public static boolean writeFile(InputStream is, String path, boolean recreate) {
        boolean res = false;
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            if (recreate && f.exists()) {
                f.delete();
            }
            if (!f.exists() && null != is) {
                File parentFile = new File(f.getParent());
                parentFile.mkdirs();
                int count = -1;
                byte[] buffer = new byte[1024];
                fos = new FileOutputStream(f);
                while ((count = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                res = true;
            }
        } catch (Exception e) {
            FileUtils.getStackTrace(e);
        } finally {
            close(fos);
            close(is);
        }
        return res;
    }

    /**
     * 把byte数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(byte[] content, String path, boolean append) {
        boolean res = false;
        File f = new File(path);
        RandomAccessFile raf = null;
        try {
            if (f.exists()) {
                if (!append) {
                    f.delete();
                    f.createNewFile();
                }
            } else {
                f.createNewFile();
            }
            if (f.canWrite()) {
                raf = new RandomAccessFile(f, "rw");
                raf.seek(raf.length());
                raf.write(content);
                res = true;
            }
        } catch (Exception e) {
            getStackTrace(e);
        } finally {
            close(raf);
        }
        return res;
    }

    /**
     * 把字符串数据写入文件
     *
     * @param content 需要写入的字符串
     * @param path    文件路径名称
     * @param append  是否以添加的模式写入
     * @return 是否写入成功
     */
    public static boolean writeFile(String content, String path, boolean append) {
        return writeFile(content.getBytes(), path, append);
    }

    /**
     * 把键值对写入文件
     *
     * @param filePath 文件路径
     * @param key      键
     * @param value    值
     * @param comment  该键值对的注释
     */
    public static void writeProperties(String filePath, String key, String value, String comment) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);// 先读取文件，再把键值对追加到后面
            p.setProperty(key, value);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            getStackTrace(e);
        } finally {
            close(fis);
            close(fos);
        }
    }

    /**
     * 根据值读取
     */
    public static String readProperties(String filePath, String key, String defaultValue) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(filePath)) {
            return null;
        }
        String value = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            value = p.getProperty(key, defaultValue);
        } catch (IOException e) {
            getStackTrace(e);
        } finally {
            close(fis);
        }
        return value;
    }

    /**
     * 把字符串键值对的map写入文件
     */
    public static void writeMap(String filePath, Map<String, String> map, boolean append, String comment) {
        if (map == null || map.size() == 0 || TextUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            Properties p = new Properties();
            if (append) {
                fis = new FileInputStream(f);
                p.load(fis);// 先读取文件，再把键值对追加到后面
            }
            p.putAll(map);
            fos = new FileOutputStream(f);
            p.store(fos, comment);
        } catch (Exception e) {
            getStackTrace(e);
        } finally {
            close(fis);
            close(fos);
        }
    }

    /**
     * 把字符串键值对的文件读入map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, String> readMap(String filePath, String defaultValue) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        Map<String, String> map = null;
        FileInputStream fis = null;
        File f = new File(filePath);
        try {
            if (!f.exists() || !f.isFile()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            Properties p = new Properties();
            p.load(fis);
            map = new HashMap<String, String>((Map) p);// 因为properties继承了map，所以直接通过p来构造一个map
        } catch (Exception e) {
            getStackTrace(e);
        } finally {
            close(fis);
        }
        return map;
    }

    public static String readInStreamString(InputStream inStream) {
        try {
            return new String(readInStream(inStream), "UTF-8");
        } catch (Exception e) {
            L.e(e);
        }
        return null;

    }

    public static byte[] readFile(File file) {
        try {
            return readInStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readInStream(InputStream inStream) {
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
            return outStream.toByteArray();
        } catch (Exception e) {
            L.i("FileTest", e.getMessage());
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception e) {
                L.e(e);
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                getStackTrace(e);
            }
        }
        return true;
    }

}
