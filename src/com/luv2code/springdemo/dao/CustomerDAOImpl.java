package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sort by lastname
		Query <Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List <Customer> customers = theQuery.getResultList();
		
		// return the results
		
		return customers;
	}



	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibernate session
		Session cunrrentSession = sessionFactory.getCurrentSession();
		
		// save the customer ... finally LOL
		cunrrentSession.saveOrUpdate(theCustomer);
		
	}



	@Override
	public Customer getCustomer(int theId) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read form database using the primary key
		Customer theCustomer = currentSession.get(Customer.class,theId);
		
		return theCustomer;
		
	}



	@Override
	public void deleteCustomer(int theId) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete Object with primary key
		@SuppressWarnings("rawtypes")
		Query theQuery = 
			  currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}



	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
        
        /* only search by name if theSearchName is not empty */
		
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;

	}

}
