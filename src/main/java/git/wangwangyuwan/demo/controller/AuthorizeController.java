package git.wangwangyuwan.demo.controller;


import git.wangwangyuwan.demo.dto.AccessTokenDTO;
import git.wangwangyuwan.demo.dto.GitHubUserDTO;
import git.wangwangyuwan.demo.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);

        String token = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO user = gitHubProvider.getUserInfo(token);
        request.getSession().setAttribute("user",user);
        return "redirect:/";
    }
}
