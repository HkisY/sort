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
//			System.out.println("ִ�н��������ʾ��");
//			System.out.println("--------------------");
//			System.out.println("����" + "\t" + "ְ��");
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
//			System.out.println("���ݿ����ݳɹ���ȡ����");
//		}
//		
//		
		
		
		//�������
		//System.out.println(o.equals( "hello"));
		//insertSort(NUMBERS);
		//shellSort(NUMBERS);
		//selectSort(NUMBERS);
		//heapSort(NUMBERS);
		//bubbleSort(NUMBERS);
		quickSort(NUMBERS);
		//���Ҳ���
		
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
	 * ���������㷨
	 */
	//��������
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
	//ϣ������
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
	//ѡ������
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
	
	
	
	//������
	public static void heapSort(int[] array){
		/* 
	     *  ��һ����������ѻ� 
	     *  beginIndex = ��һ����Ҷ�ӽڵ㡣 
	     *  �ӵ�һ����Ҷ�ӽڵ㿪ʼ���ɡ���������һ��Ҷ�ӽڵ㿪ʼ�� 
	     *  Ҷ�ӽڵ���Կ����ѷ��϶�Ҫ��Ľڵ㣬���ڵ�������Լ����Լ�����ֵΪ��� 
	     */  
		int len = array.length - 1;
		int beginIndex = (len - 1) >> 1;//��λ������൱�ڣ�len-1������2.
		for(int i =beginIndex; i >= 0; i--){
			maxHeapify(i,len,array);
		}
		/* 
	     * �ڶ������Զѻ��������� 
	     * ÿ�ζ����Ƴ����ĸ��ڵ�A[0]������β���ڵ�λ�õ�����ͬʱ�������� - 1�� 
	     * Ȼ����������������ڵ��ĩβԪ�أ�ʹ����϶ѵ����ԡ� 
	     * ֱ��δ����Ķѳ���Ϊ 0�� 
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
	 * ��������Ϊ index �������ݣ�ʹ����϶ѵ����ԡ� 
	 * 
	 * @param index ��Ҫ�ѻ���������ݵ����� 
	 * @param len   δ����Ķѣ����飩�ĳ��� 
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
			swap(cMax, index, arr);      // ������ڵ㱻�ӽڵ������  
	        maxHeapify(cMax, len, arr);
		}
	}
	
	//ð������
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
	
	//��������
	public static void quickSort(int[] array){
		_quickSort(array, 0, array.length - 1);  
	    System.out.println(Arrays.toString(array) + " quickSort");
	}
	private static int getMiddle(int[] list, int low, int high) {  
	    int tmp = list[low];    //����ĵ�һ����Ϊ����  
	    while (low < high) {  
	        while (low < high && list[high] >= tmp) {  
	            high--;  
	        }  
	        list[low] = list[high];   //������С�ļ�¼�Ƶ��Ͷ�  
	        while (low < high && list[low] <= tmp) {  
	            low++;  
	        }  
	        list[high] = list[low];   //�������ļ�¼�Ƶ��߶�  
	    }  
	    list[low] = tmp;              //�����¼��β  
	    return low;                  //���������λ��  
	}  
	private static void _quickSort(int[] list,int low, int high){
		if(low<high){
			int middle = getMiddle(list,low,high);
			_quickSort(list,low,middle - 1);
			_quickSort(list,middle + 1,high);
		}
	}
	/*
	 * ���������㷨
	 */
	//˳�����
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
	//���ֲ���
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
	//�ֿ����
	/** 
	 * �ֿ���� 
	 * @param index 
	 *            ���������зŵ��Ǹ�������ֵ 
	 * @param st 
	 *            ˳��� 
	 * @param key 
	 *            Ҫ���ҵ�ֵ 
	 * @param m 
	 *            ˳����и���ĳ�����ȣ�Ϊm 
	 * @return 
	 */  
	public static int blockSearch(int[] index,int[] st,int key ,int m){
		int i = binarySearch(key, index);
		if(i >= 0){
			int j = i > 0?i * m :i;
			int len = (i + 1) * m;
			//��ȷ���Ŀ�����˳�򷽷�����key
			for(int k = j;k < len; k++){
				if(key == st[k]){
					System.out.println("��ѯ�ɹ�");
					return k;
				}
			}
		}
		System.out.println("��ѯʧ��");
		return -1;
	} 
	//hash����
	/**** 
	 * ��ϣ�������ͨ���Լ�¼�Ĺؼ���ֵ�������㣬ֱ��������ĵ�ַ��
	 * �ǹؼ��ֵ���ַ��ֱ��ת�����������÷����Ƚϡ�����f����n����㣬
	 * RiΪ����ĳ����㣨1��i��n����keyi����ؼ���ֵ����keyi��Ri�ĵ�ַ֮�佨��ĳ�ֺ�����ϵ��
	 * ����ͨ����������ѹؼ���ֵת������Ӧ���ĵ�ַ���У�addr(Ri)=H(keyi)��addr(Ri)Ϊ��ϣ������ 
		�����ͻ�ķ������������֣����� 
		(1)���ŵ�ַ������ 
		�����������Ԫ�صĹ�ϣֵ��ͬ�����ڹ�ϣ����Ϊ����������Ԫ������ѡ��һ�����
		��������ҹ�ϣ��ʱ�����û���ڵ�һ����Ӧ�Ĺ�ϣ�������ҵ����ϲ���Ҫ�������Ԫ�أ�
		����ͻ����������ң�ֱ���ҵ�һ�����ϲ���Ҫ�������Ԫ�أ���������һ���յı������ 
		(2)����ַ�� 
		����ϣֵ��ͬ������Ԫ�ش����һ�������У��ڲ��ҹ�ϣ��Ĺ����У������ҵ��������ʱ������������Բ��ҷ����� 
	 * 
	 * 
	 * 
	 * 
	 * Hash���� 
	 *  
	 * @param hash 
	 * @param hashLength 
	 * @param key 
	 * @return 
	 */  
	public static int searchHash(int[] hash, int hashLength, int key) {  
	    // ��ϣ����  
	    int hashAddress = key % hashLength;  
	  
	    // ָ��hashAdrress��Ӧֵ���ڵ����ǹؼ�ֵ�����ÿ���Ѱַ�����  
	    while (hash[hashAddress] != 0 && hash[hashAddress] != key) {  
	        hashAddress = (++hashAddress) % hashLength;  
	    }  
	  
	    // ���ҵ��˿��ŵ�Ԫ����ʾ����ʧ��  
	    if (hash[hashAddress] == 0)  
	        return -1;  
	    return hashAddress;  
	  
	}  
	  
	/*** 
	 * ���ݲ���Hash�� 
	 *  
	 * @param hash 
	 *            ��ϣ�� 
	 * @param hashLength 
	 * @param data 
	 */  
	public static void insertHash(int[] hash, int hashLength, int data) {  
	    // ��ϣ����  
	    int hashAddress = data % hashLength;  
	  
	    // ���key���ڣ���˵���Ѿ�������ռ�ã���ʱ��������ͻ  
	    while (hash[hashAddress] != 0) {  
	        // �ÿ���Ѱַ���ҵ�  
	        hashAddress = (++hashAddress) % hashLength;  
	    }  
	  
	    // ��data�����ֵ���  
	    hash[hashAddress] = data;  
	}  
}