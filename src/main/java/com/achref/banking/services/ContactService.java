package com.achref.banking.services;

import com.achref.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);
}
