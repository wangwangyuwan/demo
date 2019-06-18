package git.wangwangyuwan.demo.provider;

import com.alibaba.fastjson.JSON;
import git.wangwangyuwan.demo.dto.AccessTokenDTO;
import git.wangwangyuwan.demo.dto.GitHubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String str = response.body().string();
            String token = str.split("&")[0].split("=")[1];
            return  token;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GitHubUserDTO getUserInfo(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String userStr = response.body().string();
            GitHubUserDTO user = JSON.parseObject(userStr, GitHubUserDTO.class);
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
