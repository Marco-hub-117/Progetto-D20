package it.unipv.ingsw.d20.DBMS;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersistentFacade pf = null;
		pf = pf.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		System.out.println(a.getAddressById("id3"));


	}

}
