import java.awt.peer.SystemTrayPeer;
import java.lang.reflect.AnnotatedArrayType;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.concurrent.BlockingDeque;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

public class Main {
	private static final int[] NUMBERS = 
		{49,38,65,97,76,13,27,78,34,12,
				64,5,4,62,99,9,102,293,234,235,
				2234,23252,2423};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		java.sql.Connection con;
//		String driver = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://localhost:3306/MySQL57";
//		String user = "root";
//		String password = "lzcgy123456!!!";
//		try{
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,user,password);
//			if(!con.isClosed())
//				System.out.println("Succeeded connecting to the Database!");
//			java.sql.Statement statement = con.createStatement();
//			String sql = "select * from emp";
//			ResultSet rs = statement.executeQuery(sql);
//			System.out.println("--------------------");
//			System.out.println("执行结果如下显示：");
//			System.out.println("--------------------");
//			System.out.println("姓名" + "\t" + "职称");
//			System.out.println("--------------------");
//			
//			
//			String job = null;
//			String id = null;
//			while(rs.next()){
//				job = rs.getString("job");
//				id = rs.getString("ename");
//				System.out.println(id + "\t" + job);
//			}
//			rs.close();
//			con.close();
//		}catch(ClassNotFoundException e){
//			System.out.println("Sorry,can`t find the Driver!");
//			e.printStackTrace();
//		}catch(SQLDataException e){
//			e.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();	
//		}finally {
//			System.out.println("数据库数据成功获取！！");
//		}
//		
//		
		
		
		//排序测试
		//System.out.println(o.equals( "hello"));
		//insertSort(NUMBERS);
		//shellSort(NUMBERS);
		//selectSort(NUMBERS);
		//heapSort(NUMBERS);
		//bubbleSort(NUMBERS);
		quickSort(NUMBERS);
		//查找测试
		
		//int resualt = orderSearch(9, NUMBERS);
		int resualt = binarySearch(9, NUMBERS);
		System.out.println("The resualt of the search is " + resualt);
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
	/*
	 * 常见排序算法
	 */
	//插入排序
	public 
	public static void insertSort(int[] array){
		for(int i = 1;i < array.length;i++){
			int temp = array[i];
			int j =i-1;
			for(;j >= 0&&array[j] > temp;j--){
				array[j + 1] = array[j];	
			}
			array[j + 1] = temp;
		}
		System.out.println(Arrays.toString(array)+"insertSort");
	}
	//希尔排序
	public static void shellSort(int[] array){
		int i,j,temp;
		int gap = 1;
		int len = array.length;
		while(gap < len/3){
			gap = gap * 3 + 1;
		}
		for(;gap >0;gap/=3){
			for(i = gap;i < len;i++){
				temp =array[i];
				for(j= i-gap;j>=0&&array[j]>temp;j-=gap){
					array[j+gap] = array[j];
				}
				array[j+gap] = temp;
			}
		}
		System.out.println(Arrays.toString(array)+"shellSort");
	}
	//选择排序
	public static void selectSort(int[] array){
		int position = 0;
		for(int i=0;i < array.length;i++){
			int j =i + 1;
			position = i;
			int temp =array[i];
			for(;j<array.length;j++){
				if(array[j]<temp){
					temp = array[j];
					position =j;
				}
			}
			array[position] = array[i];
			array[i] = temp;
		}
		System.out.println(Arrays.toString(array)+"selectSort");
	} 
	
	
	
	//堆排序
	public static void heapSort(int[] array){
		/* 
	     *  第一步：将数组堆化 
	     *  beginIndex = 第一个非叶子节点。 
	     *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。 
	     *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。 
	     */  
		int len = array.length - 1;
		int beginIndex = (len - 1) >> 1;//移位运算符相当于（len-1）除以2.
		for(int i =beginIndex; i >= 0; i--){
			maxHeapify(i,len,array);
		}
		/* 
	     * 第二步：对堆化数据排序 
	     * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。 
	     * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。 
	     * 直至未排序的堆长度为 0。 
	     */  
		for(int i =len;i>0;i--){
			swap(0,i,array);
			maxHeapify(0, i-1 ,array);
		}
		System.out.println(Arrays.toString(array)+"heapSort");
	}
	private static void swap(int i, int j,int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	/** 
	 * 调整索引为 index 处的数据，使其符合堆的特性。 
	 * 
	 * @param index 需要堆化处理的数据的索引 
	 * @param len   未排序的堆（数组）的长度 
	 */  
	private static void maxHeapify(int index, int len, int[] arr){
		int li = (index << 1) + 1;
		int ri =li + 1;
		int cMax = li;
		if(li > len){
			return;
		}
		if(ri <= len&&arr[ri]>arr[li]){cMax = ri;}
		if(arr[cMax] > arr[index]){
			swap(cMax, index, arr);      // 如果父节点被子节点调换，  
	        maxHeapify(cMax, len, arr);
		}
	}
	
	//冒泡排序
	public static void bubbleSort(int[] array) {
		int temp = 0;
		for(int i = 0;i < array.length;i++){
			for(int j =0;j<array.length - 1;j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] =temp;
				}
			}
		}
		System.out.println(Arrays.toString(array)+"bubbleSort");	
	}
	
	//快速排序
	public static void quickSort(int[] array){
		_quickSort(array, 0, array.length - 1);  
	    System.out.println(Arrays.toString(array) + " quickSort");
	}
	private static int getMiddle(int[] list, int low, int high) {  
	    int tmp = list[low];    //数组的第一个作为中轴  
	    while (low < high) {  
	        while (low < high && list[high] >= tmp) {  
	            high--;  
	        }  
	        list[low] = list[high];   //比中轴小的记录移到低端  
	        while (low < high && list[low] <= tmp) {  
	            low++;  
	        }  
	        list[high] = list[low];   //比中轴大的记录移到高端  
	    }  
	    list[low] = tmp;              //中轴记录到尾  
	    return low;                  //返回中轴的位置  
	}  
	private static void _quickSort(int[] list,int low, int high){
		if(low<high){
			int middle = getMiddle(list,low,high);
			_quickSort(list,low,middle - 1);
			_quickSort(list,middle + 1,high);
		}
	}
	/*
	 * 常见查找算法
	 */
	//顺序查找
	public static int orderSearch(int searchKey,int[] array){
		if(array.length<=0||array == null){
			return -1;
		}
		for(int i = 0;i < array.length;i++){
			if(array[i] == searchKey){
				return i;
			}
		}
		return -1;
	}
	//二分查找
	public static int binarySearch(int searchKey, int[] array){
		int low = 0;
		int high = array.length - 1;
		while(array[low]<array[high]){
			int middle = (low + high)/2;
			if (searchKey == array[middle]) {
				return middle;
			}
			else if(searchKey > array[middle]){
				low = middle - 1;
			}
			else if(searchKey < array[middle]){
				high = middle + 1;
			}
		}
		return -1;
	}
	//分块查找
	/** 
	 * 分块查找 
	 * @param index 
	 *            索引表，其中放的是各块的最大值 
	 * @param st 
	 *            顺序表， 
	 * @param key 
	 *            要查找的值 
	 * @param m 
	 *            顺序表中各块的长度相等，为m 
	 * @return 
	 */  
	public static int blockSearch(int[] index,int[] st,int key ,int m){
		int i = binarySearch(key, index);
		if(i >= 0){
			int j = i > 0?i * m :i;
			int len = (i + 1) * m;
			//在确定的块中用顺序方法查找key
			for(int k = j;k < len; k++){
				if(key == st[k]){
					System.out.println("查询成功");
					return k;
				}
			}
		}
		System.out.println("查询失败");
		return -1;
	} 
	//hash查找
	/**** 
	 * 哈希表查找是通过对记录的关键字值进行运算，直接求出结点的地址，
	 * 是关键字到地址的直接转换方法，不用反复比较。假设f包含n个结点，
	 * Ri为其中某个结点（1≤i≤n），keyi是其关键字值，在keyi与Ri的地址之间建立某种函数关系，
	 * 可以通过这个函数把关键字值转换成相应结点的地址，有：addr(Ri)=H(keyi)，addr(Ri)为哈希函数。 
		解决冲突的方法有以下两种：　　 
		(1)开放地址法　　 
		如果两个数据元素的哈希值相同，则在哈希表中为后插入的数据元素另外选择一个表项。
		当程序查找哈希表时，如果没有在第一个对应的哈希表项中找到符合查找要求的数据元素，
		程序就会继续往后查找，直到找到一个符合查找要求的数据元素，或者遇到一个空的表项。　　 
		(2)链地址法 
		将哈希值相同的数据元素存放在一个链表中，在查找哈希表的过程中，当查找到这个链表时，必须采用线性查找方法。 
	 * 
	 * 
	 * 
	 * 
	 * Hash查找 
	 *  
	 * @param hash 
	 * @param hashLength 
	 * @param key 
	 * @return 
	 */  
	public static int searchHash(int[] hash, int hashLength, int key) {  
	    // 哈希函数  
	    int hashAddress = key % hashLength;  
	  
	    // 指定hashAdrress对应值存在但不是关键值，则用开放寻址法解决  
	    while (hash[hashAddress] != 0 && hash[hashAddress] != key) {  
	        hashAddress = (++hashAddress) % hashLength;  
	    }  
	  
	    // 查找到了开放单元，表示查找失败  
	    if (hash[hashAddress] == 0)  
	        return -1;  
	    return hashAddress;  
	  
	}  
	  
	/*** 
	 * 数据插入Hash表 
	 *  
	 * @param hash 
	 *            哈希表 
	 * @param hashLength 
	 * @param data 
	 */  
	public static void insertHash(int[] hash, int hashLength, int data) {  
	    // 哈希函数  
	    int hashAddress = data % hashLength;  
	  
	    // 如果key存在，则说明已经被别人占用，此时必须解决冲突  
	    while (hash[hashAddress] != 0) {  
	        // 用开放寻址法找到  
	        hashAddress = (++hashAddress) % hashLength;  
	    }  
	  
	    // 将data存入字典中  
	    hash[hashAddress] = data;  
	}  
}