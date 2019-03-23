package misc;

import java.awt.EventQueue;
import java.util.Date;

import controller.DataLoader;
import controller.ExpenseContainerApi;
import controller.ExpenseContainerImpl;
import controller.ExpenseSubject;
import controller.FileLoaderImpl;
import controller.InMemoryStore;
import controller.Store;
import utils.Constants;
import view.ExpenseObserverImpl;
import view.UserInterface;

public class PBMMain {

	public static void main(String[] args) {
		DataLoader loader = new FileLoaderImpl(Constants.SAMPLE_DATA_FILENAME);
		Store dataStore= new InMemoryStore(loader);
		ExpenseContainerApi container = (ExpenseContainerApi)ExpenseContainerImpl.getInstance();
		ExpenseSubject subject = (ExpenseSubject)ExpenseContainerImpl.getInstance();
		container.init(dataStore);
		ExpenseObserverImpl.getInstance().init(subject);
		subject.start();
		UserInterface frame = UserInterface.getInstance();
		
		System.out.println("Start PBM " + new Date());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
