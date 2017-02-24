package com.zhaixt;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhaixiaotong on 2016-11-29.
 */
public class HDFSDemo {
    public static Configuration conf = new Configuration();

    static{
        System.out.println("======hehe=======");


        Configuration conf = HBaseConfiguration.create();
        //        conf.set("hbase.master", "master:60000");
        conf.set("hbase.zookeeper.quorum", "master,slave1,slave2");
        conf.set("hbase.zookeeper.property.clientport", "2181");

    }

    //在指定位置新建一个文件，并写入字符
    @SuppressWarnings("static-access")
    public static void WriteToHDFS(String file, String words) throws IOException, URISyntaxException {
        Configuration conf = new Configuration();
        //        conf.set("hadoop.job.ugi", "admin,admin");
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);

        // 正常创建文件
        //        FSDataOutputStream out = fs.create(path); //创建文件

        // 创建 chmod 的文件
        FsPermission permission = FsPermission.createImmutable((short) 0777);
        FSDataOutputStream out = fs.create(fs, path, permission);

        //两个方法都用于文件写入，好像一般多使用后者
        out.writeBytes(words);
        out.write(words.getBytes("UTF-8"));

        out.close();
        //如果是要从输入流中写入，或是从一个文件写到另一个文件（此时用输入流打开已有内容的文件）
        //可以使用如下IOUtils.copyBytes方法。
        //FSDataInputStream in = fs.open(new Path(args[0]));
        //IOUtils.copyBytes(in, out, 4096, true)        //4096为一次复制块大小，true表示复制完成后关闭流
    }

    @SuppressWarnings("static-access")
    public static void WriteLocalFileToHDFS(String file, String localFile) throws IOException {
        //        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path inputDir = new Path(file);//1.设定输入目录与输出文件
        // 创建 chmod 的文件
        FsPermission permission = FsPermission.createImmutable((short) 0777);
        FSDataOutputStream out = fs.create(fs, inputDir, permission);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(localFile)));
        String line = "";
        while (StringUtils.isNotBlank(line = br.readLine())) {
            System.out.println(line);
            out.write((line + "\r\n").getBytes("UTF-8"));
        }
        out.close();
        br.close();
    }

    public static void ReadFromHDFS(String file) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);
        FSDataInputStream in = fs.open(path);

        IOUtils.copyBytes(in, System.out, 4096, true);
        //使用FSDataInoutStream的read方法会将文件内容读取到字节流中并返回
        /**
         * FileStatus stat = fs.getFileStatus(path); // create the buffer byte[]
         * buffer = new byte[Integer.parseInt(String.valueOf(stat.getLen()))];
         * is.readFully(0, buffer); is.close(); fs.close(); return buffer;
         */
    }

    public static void DeleteHDFSFile(String file) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(file), conf);
        Path path = new Path(file);
        //查看fs的delete API可以看到三个方法。deleteonExit实在退出JVM时删除，下面的方法是在指定为目录是递归删除

        fs.delete(path, true);
        fs.close();
    }

    public static void UploadLocalFileHDFS(String src, String dst) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        Path pathDst = new Path(dst);
        Path pathSrc = new Path(src);

        fs.copyFromLocalFile(pathSrc, pathDst);
        fs.close();
    }

    public static void ListDirAll(String DirFile) throws IOException {



        FileSystem fs = FileSystem.get(URI.create(DirFile), conf);
        Path path = new Path(DirFile);

        FileStatus[] status = fs.listStatus(path);
        //方法1
        for (FileStatus f : status) {
            System.out.println(f.getPath().toString());
        }
        //方法2
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p.toString());
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("======heheda=======");
        UserGroupInformation.setConfiguration(conf);


        //下面做的是显示目录下所有文件
        String filePath = "hdfs://master:8020/user/admin/cmp";
        ListDirAll(filePath);

        //        String fileWrite = "hdfs://master:8020/user/admin/cmp/bobo.csv";
        String fileWrite2 = "hdfs://master:8020/user/admin/hdfs.dat";
        //        String words = "This words is to write into file!\n";

        String localFile = "/Users/apple/Desktop/t.dat";
        // 将本地文件写到hdfs上面
//        WriteLocalFileToHDFS(fileWrite2, localFile);
//
//        String readFile = "hdfs://master:8020/user/admin/hdfs.dat";
//
//        //        WriteToHDFS(fileWrite, words);
//        //这里我们读取fileWrite的内容并显示在终端
//        ReadFromHDFS(readFile);
        //        //这里删除上面的fileWrite文件
        //        DeleteHDFSFile(fileWrite);
        //假设本地有一个uploadFile，这里上传该文件到HDFS
        //      String LocalFile = "file:///home/kqiao/hadoop/MyHadoopCodes/uploadFile";
        //      UploadLocalFileHDFS(LocalFile, fileWrite    );
    }
}
