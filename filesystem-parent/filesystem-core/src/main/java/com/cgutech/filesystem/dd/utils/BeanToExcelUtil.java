package com.cgutech.filesystem.dd.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgutech.filesystem.dd.dto.UserBean;
import com.cgutech.filesystem.utils.ExcelUtil;

@Service
public class BeanToExcelUtil<T> {

	@Autowired
	private ExcelUtil excelUtil;
	private final String EXCEL_SAVE_PATH = "D:\\EXCEL";

	/**
	 * 
	 * @author Becan E-mail:becan@cgutech.com
	 * @date 2016年9月6日 上午10:32:56
	 * @param beanList 需要转为excel的javabean对象列表
	 * @param beanClass javaBean对象的Class类
	 * @retrun void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void writeToFile(List<T> beanList, Class beanClass)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, FileNotFoundException, IOException {
		List<String[]> titles = new ArrayList<String[]>();
		String[] sheetName = { "用户信息" };
		String savePath = EXCEL_SAVE_PATH + File.separator + beanClass.getName()+".xlsx";
		Field[] fields1 = beanClass.getDeclaredFields();
		int len = fields1.length;
		int temp =len;
		Field[] fields =new Field[len];
		for (int i=0,j=0;i<temp;i++,j++) {
			if(fields1[i].getName().toString().equals("serialVersionUID")){
				len =len-1;
				j--;
				continue;
			}
			fields[j]=fields1[i];
		}
		String[] title = new String[len];
		for (int i = 0; i < len; i++) {
			title[i] = fields[i].getName();
		}
		titles.add(title);

		List<String[]> datas = new ArrayList<String[]>();
		List<List<String[]>> data_ = new ArrayList<>();
		for (int j = 0; j < beanList.size(); j++) {
			T bean = beanList.get(j);
			String[] data = new String[len];
			for (int i = 0; i < len; i++) {
				String filedname = fields[i].getName();
				String getname = "get" + filedname.substring(0, 1).toUpperCase() + filedname.substring(1);
				Object object=beanClass.getDeclaredMethod(getname, null).invoke(bean, null);
				if(object==null){
					data[i]="";
				}else {
					data[i]=object.toString();
				}
			}
			datas.add(data);
		}
		data_.add(datas);
		excelUtil.writeToFile(savePath, sheetName, titles, data_);
	}
	public static void main(String[] args) {
		UserBean userBean = new UserBean();
		userBean.setIsAdmin(false);
		long[] pare = {111,32333};
		System.out.println(pare);
		userBean.setDepartment(pare);
		List<UserBean> beans = new ArrayList<>();
		beans.add(userBean);
		try {
			new BeanToExcelUtil<UserBean>().writeToFile(beans, UserBean.class);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String data =null;
		Field[] fields1 = UserBean.class.getDeclaredFields();
		int len = fields1.length;
		int temp = len;
		//System.out.println(len);
		Field[] fields =new Field[len];
		for (int i=0,j=0;i<temp;i++,j++) {
			if(fields1[i].getName().toString().equals("serialVersionUID")){
				len =len-1;
				j--;
				continue;
			}
			fields[j]=fields1[i];
		}
		//System.out.println(len);
		for (int i=0;i<len;i++) {
			String filedname = fields[i].getName();
			String getname = "get" + filedname.substring(0, 1).toUpperCase() + filedname.substring(1);
			System.out.println(i+"--"+getname);
				try {
					Object object=UserBean.class.getDeclaredMethod(getname, null).invoke(userBean, null);
					if(object==null){
						data="";
					}else {
						data=object.toString();
					}
					
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(data);
			
		}*/
	}
}
