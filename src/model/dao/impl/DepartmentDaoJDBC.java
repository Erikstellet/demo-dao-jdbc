package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao
{
	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) 
	{
		this.conn = conn;
	}

	@Override
	public void insert(Department obj)
	{
		

	}

	@Override
	public void update(Department obj)
	{
		

	}

	@Override
	public void deleteById(Integer id)
	{
		

	}

	@Override
	public Department findById(Integer id)
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try 
		{
			st = conn.prepareStatement
			(
				"SELECT * "	
				+"FROM department "
				+"WHERE id = ?"		
			);
		
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next())
			{
				Department dep = InstantiateDepartment(rs);
				return dep;
			}
			
			return null;
		
		}
		catch (Exception e)
		{
			throw new DbException(e.getMessage());
		}
		finally 
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Department> findAll()
	{
		PreparedStatement st = null;
		ResultSet rs =null;
		
		try
		{
			st = conn.prepareStatement
			(
				"SELECT * "
				+"FROM department "
				+"ORDER BY Name"
			);
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next())
			{
				Department dep = InstantiateDepartment(rs);
				list.add(dep);
			}
			return list;
		}
		catch (Exception e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}


	private Department InstantiateDepartment(ResultSet rs) throws SQLException
	{	
		Department dep = new Department();
		
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public Department findBySeller(Seller seller) 
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try
		{ 
			st = conn.prepareStatement
			(
				"SELECT d.*, s.Name as SellName "
				+"FROM department d join seller s "
				+"ON d.Id = s.DepartmentId "
				+"WHERE s.Id = ?"
			);
			
			st.setInt(1, seller.getId());
			
			rs = st.executeQuery();
			
			if(rs.next())
			{
				Department dep = InstantiateDepartment(rs);
				return dep;
			}
			
			return null;
		}
			
		catch (Exception e)
		{
				throw new DbException(e.getMessage());
		}	
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
