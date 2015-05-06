package com.newswatch;

import java.io.ByteArrayInputStream;

import com.newswatch.interfaces.BaseInterface;
import com.newswatch.utils.securitycode.SecurityCode;
import com.newswatch.utils.securitycode.SecurityImage;

/**
 * 提供图片验证码
 * @version 1.0 2012/08/22
 * @author dongliyang
 */
public class SecurityCodeImageAction extends BaseAction implements BaseInterface {
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //放入session中
        getSession().setAttribute(SESSION_SECURITY_CODE, securityCode);
        return SUCCESS;
    }
}