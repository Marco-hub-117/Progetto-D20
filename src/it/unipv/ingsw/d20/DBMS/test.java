package it.unipv.ingsw.d20.DBMS;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersistenceFacade pf = null;
		pf = pf.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		a.addVending("id4", "Roma");
		System.out.println(a.getAddressById("id3"));


	}

}
