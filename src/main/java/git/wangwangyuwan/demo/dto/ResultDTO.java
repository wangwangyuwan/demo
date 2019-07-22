package git.wangwangyuwan.demo.dto;

import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDTO {
    private String code;
    private String message;


    public static ResultDTO errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException errorCode){
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(String code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode("200");
        resultDTO.setMessage("成功！");
        return resultDTO;
    }

}
