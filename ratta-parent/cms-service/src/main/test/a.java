import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * ******Title:<p> 程序的奥秘 </p>******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 <p> ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/10/28 13:56
 */
public class a {
    public static void main(String[] args) {
        URL whatismyip = null;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine(); //you get the IP as a String
            System.out.println(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
