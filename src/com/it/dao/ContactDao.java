package com.it.dao;

import com.it.entity.Contact;
import com.it.utils.DataSourceUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 联系人的数据访问层
 */
public class ContactDao {

    //得到数据源
    private JdbcTemplate template = new JdbcTemplate(DataSourceUtils.getDataSource());

    /**
     * 查询所有的联系人
     */
    public List<Contact> findAll() {
        return template.query("select * from contact", new BeanPropertyRowMapper<>(Contact.class));
    }

    /**
     * 通过id查询1个联系人
     */
    public Contact findById(int id) {
        return template.queryForObject("select * from contact where id=?", new BeanPropertyRowMapper<>(Contact.class), id);
    }

}
