package kr.coding.lets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class HomeController {
    final String CLIENT_APP_KEY = "1bb9b32f63329982713aba6cbd591e8e";
    final String CLIENT_SECRET = "qdjYrTR1Kf7Gk2XtToGTtKYVpntIfntC";
    final String REDIRECT_URI = "http://127.0.0.1:8080/login";

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name", "LEEHG");

        return "index";
    }
    @RequestMapping("/login")
    public String login(Model model, @RequestParam("code")String code) throws UnsupportedEncodingException{
        // final String accessToken = getAccessToken(code);

        model.addAttribute("name", "LOGIN");

        return "index";
    }
    @GetMapping("/kakaoAuth")
    public String kakaoConnect(){
        StringBuilder url = new StringBuilder();
        url.append("https://kauth.kakao.com/oauth/authorize?client_id=")
            .append(CLIENT_APP_KEY)
            .append("&redirect_uri=").append(REDIRECT_URI)
            .append("&response_type=code");
        return "redirect:" + url.toString();
    }

    public String getAccessToken (String authorize_code) throws UnsupportedEncodingException {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(CLIENT_APP_KEY); //수정 할것
            sb.append("&redirect_uri=").append(REDIRECT_URI); //수정 할것
            sb.append("&client_secret=").append(CLIENT_SECRET); //수정 할것
            sb.append("&code=").append(authorize_code);
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return access_Token;
    }
}
