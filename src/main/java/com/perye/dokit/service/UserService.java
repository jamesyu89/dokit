package com.perye.dokit.service;

import com.perye.dokit.dto.UserDTO;
import com.perye.dokit.dto.UserQueryCriteria;
import com.perye.dokit.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CacheConfig(cacheNames = "user")
public interface UserService {

    /**
     * get
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    UserDTO findById(long id);

    /**
     * create
     * @param resources
     * @return
     */
    @CacheEvict(allEntries = true)
    UserDTO create(User resources);

    /**
     * update
     * @param resources
     */
    @CacheEvict(allEntries = true)
    void update(User resources);

    /**
     * delete
     * @param id
     */
    @CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * findByName
     * @param userName
     * @return
     */
    @Cacheable(key = "'loadUserByUsername:'+#p0")
    UserDTO findByName(String userName);

    /**
     * 修改密码
     * @param username
     * @param encryptPassword
     */
    @CacheEvict(allEntries = true)
    void updatePass(String username, String encryptPassword);

    /**
     * 修改头像
     * @param file
     */
    @CacheEvict(allEntries = true)
    void updateAvatar(MultipartFile file);

    /**
     * 修改邮箱
     * @param username
     * @param email
     */
    @CacheEvict(allEntries = true)
    void updateEmail(String username, String email);

    @Cacheable
    Object queryAll(UserQueryCriteria criteria, Pageable pageable);

    @Cacheable
    List<UserDTO> queryAll(UserQueryCriteria criteria);

    void download(List<UserDTO> queryAll, HttpServletResponse response) throws IOException;
}
