package com.it.service;

import com.it.dao.ContactDao;
import com.it.entity.Contact;

import java.util.List;

/**
 * 业务层，Model层
 */
public class ContactService {

    private ContactDao contactDao = new ContactDao();

    /**
     * 调用方法查询所有的联系人
     */
    public List<Contact>  findAllContacts() {
        return contactDao.findAll();
    }

    /**
     * 通过id查询1个联系人
     */
    public Contact findById(int id) {
        return contactDao.findById(id);
    }


}
