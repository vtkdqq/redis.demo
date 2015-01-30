package com.resis.user.service;

import com.resis.bean.User;

public interface IUserService
{
    public boolean add(User user);

    /**
     * 删除 <br>
     * ------------------------------<br>
     * 
     * @param key
     */
    void delete(String key);

    /**
     * 修改 <br>
     * ------------------------------<br>
     * 
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 通过key获取 <br>
     * ------------------------------<br>
     * 
     * @param keyId
     * @return
     */
    User get(String keyId);

}
