package nio2;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * Created by zhaixiaotong on 2016-8-10.
 */

//这只是个test，没用nip
public class TestClient {
    // 本地字符集
    public static void main(String[] args) throws Exception{

        Socket socket=new Socket("127.0.0.1",8888);

        InputStream  inStram=socket.getInputStream();
        OutputStream outStream=socket.getOutputStream();

        // 输出
        PrintWriter out=new PrintWriter(outStream,true);

        out.print("getPublicKey！");
        out.flush();

        socket.shutdownOutput();// 输出结束

        // 输入
        Scanner in=new Scanner(inStram);
        StringBuilder sb=new StringBuilder();
        while(in.hasNextLine()){
            String line=in.nextLine();
            sb.append(line);
        }
        String response=sb.toString();
        System.out.println("response="+response);
    }
}
