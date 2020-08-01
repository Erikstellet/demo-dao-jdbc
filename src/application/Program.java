package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program 
{
	public static void main(String[] args) 
	{	
		SellerDao sellerDao =  DaoFactory.createSellerDao();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==================================== Test 1 - SellerFindById  ==================================== \n");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		System.out.println("================================ Test 2 - SellerFindByDepartment  ================================ \n");
		Department department = new Department(2, null);
		
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj: list)
		{
			System.out.println(obj);
			System.out.println("");
		}
		
		System.out.println("=================================== Test 3 - SellerFindAll  =================================== \n");
		
		list = sellerDao.findAll();
		
		for(Seller obj: list)
		{
			System.out.println(obj);
			System.out.println("");
		}
		
		System.out.println("=================================== Test 4 - Seller Insert  =================================== \n");
		
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id: " + newSeller.getId());
	

		System.out.println("\n =================================== Test 5 - Seller Update  =================================== \n");
		
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		seller.setEmail("Martha@gmail.com");
		sellerDao.update(seller);
		
		System.out.println("Update: " + seller);
	
		System.out.println("\n =================================== Test 6 - Seller Delete  =================================== \n");
	
		System.out.println("Enter ID for delete test: ");
		int id = sc.nextInt();
		
		sellerDao.deleteById(id);
		
		System.out.println("Delete Completed!");
		
		sc.close();
	}
	
}