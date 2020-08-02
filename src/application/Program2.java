package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2
{
	public static void main(String[] args) 
	{	
		DepartmentDao depDao =  DaoFactory.createDepartmentDao();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==================================== Test 1 - DepartmentFindById  ==================================== \n");
	
		Department dep = depDao.findById(3);
		System.out.println(dep);
		
		System.out.println("\n================================ Test 2 - DepartmentFindBySeller  ================================ \n");
		
		Seller sell = new Seller(2, null , null, null, null, null);

		Department depDois = depDao.findBySeller(sell);
		System.out.println(depDois);

		System.out.println("\n=================================== Test 3 - DepartmentFindAll  =================================== \n");
		
		List<Department> list = depDao.findAll();
		
		for(Department obj: list)
		{
			System.out.println(obj);
		}
		
		System.out.println("\n=================================== Test 4 - Department Insert  =================================== \n");
		
	
		System.out.println("\n=================================== Test 5 - Department Update  =================================== \n");
		
	
		System.out.println("\n=================================== Test 6 - Department Delete  =================================== \n");
	
	
		sc.close();
	}
}