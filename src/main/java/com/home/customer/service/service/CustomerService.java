package com.home.customer.service.service;

import com.home.customer.service.entity.Customer;
import com.home.customer.service.repository.CustomerRepository;
import com.home.customer.service.vo.Product;
import com.home.customer.service.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Customer saveUser(Customer customer) {
        log.info("Inside saveCustomer of CustomerService");
        return repository.save(customer);
    }

    public ResponseTemplateVO getCustomerWithProduct(Long customerId) {
        log.info("Inside getCustomerWithProduct of CustomerService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Customer customer = repository.findByCustomerId(customerId);

        /*Product product =
                restTemplate.getForObject("http://localhost:9001/product-service/" + customer.getCustomerId()
                        ,Product.class); */

        Product product =
                restTemplate.getForObject("http://PRODUCT-SERVICE/product-service/" + customer.getCustomerId()
                        ,Product.class);    // we can use application name as domain name
        // after adding this we will get this java.net.UnknownHostException: PRODUCT-SERVICE exception
        // we need to add the loadbalancer to the resttamplate bean

        vo.setCustomer(customer);
        vo.setProduct(product);

        return  vo;
    }

}
