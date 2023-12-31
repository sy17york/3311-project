package nutrifit;

import javax.swing.*;

import nutrifit.ReadWriteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MealLoggingPage extends JFrame implements ActionListener {
	private JButton back;
	private JButton addIngredient;
	private JButton changeDate;
	private JList ingredients;
	private DefaultListModel ingredientsModel;
	private JLabel mealExistsSuccessOrFail;
    private JTextArea tout;
    private JComboBox tfood;
    private JLabel food;
    private JTextField tamount;
    private JLabel amount;
    private JComboBox meals;
    private JLabel meal;
    private JButton sub;
    private JLabel date;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    private JLabel successOrFail;
    private JButton search;
    private int i =0;
    private JButton submitMeal;
    private boolean mealExists = true;
    //private DefaultListModel modelFoods;
    
    MealLoggingState state;
    
    
    private class IngredientToBeAdded{
    	private char meal;
    	private int foodID;
    	private int amountEaten;
    	private Date date;
    	
    	public IngredientToBeAdded(char meal, int foodID, int amountEaten, Date date) {
			super();
			this.meal = meal;
			this.foodID = foodID;
			this.amountEaten = amountEaten;
			this.date = date;
		}
		public char getMeal() {
			return meal;
		}
		public void setMeal(char meal) {
			this.meal = meal;
		}
		public int getFoodID() {
			return foodID;
		}
		public void setFoodID(int foodID) {
			this.foodID = foodID;
		}
		public int getAmountEaten() {
			return amountEaten;
		}
		public void setAmountEaten(int amountEaten) {
			this.amountEaten = amountEaten;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
    }
    
    private ArrayList<IngredientToBeAdded> ingredientsToBeAdded = new ArrayList<IngredientToBeAdded>();
    
    public void clearFood() {
    	ingredientsToBeAdded.clear();
    }
    
    ReadWriteController dataStorage = ReadWriteController.getInstance();
	
    //dataStorage.createNewTable();
    
    private String possibleMeals[]
            = { "breakfast","lunch","dinner","snack"};
    

    
    private String possibleFoods[] = dataStorage.foodNames();
    
    public MealLoggingPage() throws IOException {

        setTitle("Meal Logging Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        //c.setBackground(Color.PINK);
        c.setLayout(null);

        String s1 = ""; //dataStorage.debugDumpDatabase();
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 100);
        tout.setLocation(500, 350);
        tout.setEditable(false);
        c.add(tout);

        tout.setText(s1);
        
        ingredientsModel = new DefaultListModel();

        
        ingredients = new JList(ingredientsModel);
        ingredients.setFont(new Font("Arial", Font.PLAIN, 15));
        ingredients.setSize(300, 200);
        ingredients.setLocation(500, 100);
        c.add(ingredients);
        
        meal = new JLabel("Enter Meal");
        meal.setFont(new Font("Arial", Font.PLAIN, 15));
        meal.setSize(1000, 20);
        meal.setLocation(100, 75);
        c.add(meal);
        
        meals = new JComboBox(possibleMeals);
        meals.setFont(new Font("Arial", Font.PLAIN, 15));
        meals.setSize(100, 20);
        meals.setLocation(100, 100);
        c.add(meals);
        
        date = new JLabel("Enter Date of Meal (DD MM YYYY)");
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(1000, 20);
        date.setLocation(100, 150);
        c.add(date);
        
        day = new JTextField();
        day.setFont(new Font("Arial", Font.PLAIN, 15));
        day.setSize(25, 20);
        day.setLocation(100, 175);
        c.add(day);
        
        month = new JTextField();
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(25, 20);
        month.setLocation(130, 175);
        c.add(month);
        
        year = new JTextField();
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(50, 20);
        year.setLocation(160, 175);
        c.add(year);
        
        submitMeal = new JButton("Set Date and Meal");
        submitMeal.setFont(new Font("Arial", Font.PLAIN, 15));
        submitMeal.setSize(200, 20);
        submitMeal.setLocation(100, 225);
        submitMeal.addActionListener(this);
        c.add(submitMeal);
        
        changeDate = new JButton("Change it");
        changeDate.setFont(new Font("Arial", Font.PLAIN, 15));
        changeDate.setSize(120, 20);
        changeDate.setLocation(320, 225);
        changeDate.addActionListener(this);
        c.add(changeDate);
        
        mealExistsSuccessOrFail = new JLabel("Enter Meal and Date before entering ingredients");
        mealExistsSuccessOrFail.setFont(new Font("Arial", Font.PLAIN, 18));
        mealExistsSuccessOrFail.setSize(1000, 30);
        mealExistsSuccessOrFail.setLocation(100, 250);
        c.add(mealExistsSuccessOrFail);
        
        
        /*changeMeal = new JButton("Change it");
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(150, 20);
        year.setLocation(100, 225);*/

        
        

        /*edit = new JButton("edit");
        edit.setFont(new Font("Arial", Font.PLAIN, 15));
        edit.setSize(100, 20);
        edit.setLocation(270, 450);
        edit.addActionListener(this);
        c.add(edit);*/

        setVisible(true);
        
        food = new JLabel("Enter Food Eaten");
        food.setFont(new Font("Arial", Font.PLAIN, 15));
        food.setSize(1000, 20);
        food.setLocation(100, 300);
        c.add(food);
        
   
        
        //tfood = new JComboBox(possibleFoods);
        tfood=new JComboBox();
        tfood.setFont(new Font("Arial", Font.PLAIN, 15));
        tfood.setSize(400, 20);
        tfood.setLocation(100, 325);
        //tfood.setEditable(true);
        c.add(tfood);
        
        search = new JButton("Search");
        search.setFont(new Font("Arial", Font.PLAIN, 12));
        search.setSize(80, 20);
        search.setLocation(10, 325);
        search.addActionListener(this);
        //sub.setEnabled(false);
        c.add(search);
        
        amount = new JLabel("Enter Amount Eaten (in grams)");
        amount.setFont(new Font("Arial", Font.PLAIN, 15));
        amount.setSize(1000, 20);
        amount.setLocation(100, 375);
        c.add(amount);
        
        tamount = new JTextField();
        tamount.setFont(new Font("Arial", Font.PLAIN, 15));
        tamount.setSize(100, 20);
        tamount.setLocation(100, 400);
        c.add(tamount);
        
       
        

        sub = new JButton("Submit Meal");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(150, 20);
        sub.setLocation(100, 500);
        sub.addActionListener(this);
        //sub.setEnabled(false);
        c.add(sub);
        
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(270, 500);
        back.addActionListener(this);
        c.add(back);
        
        addIngredient = new JButton("Add Ingredient");
        addIngredient.setFont(new Font("Arial", Font.PLAIN, 15));
        addIngredient.setSize(200, 40);
        addIngredient.setLocation(100, 450);
        addIngredient.addActionListener(this);
        //addIngredient.setEnabled(false);
        c.add(addIngredient);
        
        successOrFail = new JLabel("");
        successOrFail.setFont(new Font("Arial", Font.PLAIN, 20));
        successOrFail.setSize(1000, 20);
        successOrFail.setLocation(100, 525);
        c.add(successOrFail);
           
          
        /*tfood.setEnabled(false);
    	tamount.setEditable(false);    
    	changeDate.setEnabled(false);*/
        
        state = new NoDateNoFoodState(this);
        state.handle();
           
   }
    
    /*private JButton back;
	private JButton addIngredient;*/
    
    public void addIngredientState(boolean eOrD) {
    	addIngredient.setEnabled(eOrD);

    }
    
    public void searchState(boolean eOrD) {
    	search.setEnabled(eOrD);

    }
    
    /*
	private JButton changeDate;*/
  
    public void changeDateState(boolean eOrD) {

    	changeDate.setEnabled(eOrD);
    }
    
    
    
/*	private JList ingredients;*/
    
    /*
	private DefaultListModel ingredientsModel;
	private JLabel mealExistsSuccessOrFail;
    private JTextArea tout;
    private JComboBox tfood;
    */
    
    public void foodComboBoxState(boolean eOrD) {

    	tfood.setEditable(eOrD);
    	tfood.setEnabled(eOrD);
    	
    }
    
    /*
    
    private JLabel food;
    private JTextField tamount;
    */
    
    public void amountTextFieldState(boolean eOrD) {

    	tamount.setEditable(eOrD);
    	
    }
    
    
    /*
    private JLabel amount;
    private JComboBox meals;
    */
    public void typeOfMealComboBoxState(boolean eOrD) {

    	meals.setEnabled(eOrD);
   
    }
    
    
    /*
    private JLabel meal;
    private JButton sub;
    */
    
    public void submitButtonState(boolean eOrD) {

    	sub.setEnabled(eOrD);
    }
    
    /*
    private JLabel date;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    */
    public void enterDateState(boolean eOrD) {

		day.setEditable(eOrD);
		month.setEditable(eOrD);
		year.setEditable(eOrD);
    	
    }
    /*
    private JLabel successOrFail;
    private int i =0;
    private JButton submitMeal;*/
    
    public void setDateAndMealButtonState(boolean eOrD) {

    	submitMeal.setEnabled(eOrD);
    	
    }

		
		/*public static void main(String[] args) throws IOException, InterruptedException, Exception {  
			MealLoggingPage test = new MealLoggingPage();
	    } */ 


		
   @Override
    public void actionPerformed(ActionEvent e) {
	   
	   		
            if(e.getSource() == back){
            	ingredientsModel.clear();
            	ingredientsToBeAdded.clear();
            	i=0;
                setVisible(false);
                try {
					new HomePage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if(e.getSource() == submitMeal) {
            	System.out.println("submited meal and date");
            	char selectedMeal = 'n';
            	
            	String selectedMealString = (String)meals.getSelectedItem();
            	
            	if (selectedMealString.equals("breakfast")) {
            		selectedMeal = 'b';
            	}
            	else if (selectedMealString.equals("lunch")) {
            		selectedMeal = 'l';
            	}
            	else if (selectedMealString.equals("dinner")) {
            		selectedMeal = 'd';
            	}
            	else if (selectedMealString.equals("snack")) {
            		selectedMeal = 's';
            	}
            	
            	int dayInt = Integer.valueOf(day.getText());
            	int monthInt = Integer.valueOf(month.getText()) - 1;
            	int YearInt = Integer.valueOf(year.getText()) - 1900;
            	
            	Date d1 = new Date(YearInt,monthInt,dayInt); 
            	//long dateLong = d1.getTime();
            	
            	
            	mealExists = dataStorage.doesMealExist(selectedMeal, d1);
            	if (mealExists) {
            		mealExistsSuccessOrFail.setText("ERROR: Meal Already Entered");
            	}
            	else {
            		
            		mealExistsSuccessOrFail.setText("Please Enter Ingredients");
            		
            		state = state.switchState(MealLoggingEvent.SETDATEANDMEAL);
            		state.handle();
            		
            		/*submitMeal.setEnabled(false);
                	//sub.setEnabled(true);
                	addIngredient.setEnabled(true);
                	day.setEditable(false);
                	month.setEditable(false);
                	year.setEditable(false);
                	meals.setEnabled(false);
                	tfood.setEnabled(true);
                	tamount.setEditable(true);
                	changeDate.setEnabled(true);
                	if (ingredientsToBeAdded.size()!= 0) {
                		sub.setEnabled(true);
                	}*/
            		
            	}
            	
            }
            else if (e.getSource()== changeDate) {
            	/*submitMeal.setEnabled(true);
            	sub.setEnabled(false);
            	meals.setEnabled(true);
            	day.setEditable(true);
            	month.setEditable(true);
            	year.setEditable(true);
            	tfood.setEnabled(false);
            	tamount.setEditable(false);
            	addIngredient.setEnabled(false);
            	changeDate.setEnabled(false);*/
            	
            	mealExistsSuccessOrFail.setText("Please Enter Another Date");
            	
            	state = state.switchState(MealLoggingEvent.CHANGEIT);
        		state.handle();
            }
            else if(e.getSource() == addIngredient){
            	
            	String foodChosen = (String)tfood.getSelectedItem();
            	int amountEaten = Integer.valueOf(tamount.getText());
            	String selectedMealString = (String)meals.getSelectedItem();
            	char selectedMeal = 's';
            	int foodID =0;
            	if (selectedMealString.equals("breakfast")) {
            		selectedMeal = 'b';
            	}
            	else if (selectedMealString.equals("lunch")) {
            		selectedMeal = 'l';
            	}
            	else if (selectedMealString.equals("dinner")) {
            		selectedMeal = 'd';
            	}
            	else if (selectedMealString.equals("snack")) {
            		selectedMeal = 's';
            	}
            	foodID = dataStorage.IDOfAGivenFood(foodChosen);
            	int dayInt = Integer.valueOf(day.getText());
            	int monthInt = Integer.valueOf(month.getText()) - 1;
            	int YearInt = Integer.valueOf(year.getText()) - 1900;
            	
            	Date d1 = new Date(YearInt,monthInt,dayInt);
            	
            	String display = foodChosen +", "+amountEaten+"g";
            	ingredientsModel.add(i,display);
            	i++;
            	
            	IngredientToBeAdded a1 = new IngredientToBeAdded(selectedMeal, foodID, amountEaten, d1);
            	System.out.println(selectedMeal+", "+foodID+", "+amountEaten);
            	
            	
            	ingredientsToBeAdded.add(a1);
            	
            	state = state.switchState(MealLoggingEvent.ADDINGREDIENT);
        		state.handle();
            	//sub.setEnabled(true);
            }
            
            else if (e.getSource() == sub) {
            	
            	
            	dataStorage.createNewTable();
            	//System.out.println(tfood.getText()+" "+(String)meals.getSelectedItem() +" " + tamount.getText());
            	
            	for (int j = 0; j<ingredientsToBeAdded.size(); j++) {
            		IngredientToBeAdded a1 = ingredientsToBeAdded.get(j);
            		try {
    					dataStorage.storeMeal(a1.getMeal(),a1.getFoodID() , a1.getAmountEaten(),a1.getDate());
    					successOrFail.setText("Meal Log Successfully Entered");
    				} catch (Exception e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    					successOrFail.setText("ERROR");
    				}
            	}
            	
            	//clearFood();
            	//ingredientsToBeAdded.clear();
            	i=0;

            	String output = dataStorage.debugDumpDatabase();
            	tout.setText(output);
            	
            	/*submitMeal.setEnabled(true);
            	sub.setEnabled(false);
            	meals.setEnabled(true);
            	day.setEditable(true);
            	month.setEditable(true);
            	year.setEditable(true);
            	tfood.setEnabled(false);
            	tamount.setEditable(false);
            	addIngredient.setEnabled(false);
            	changeDate.setEnabled(false);*/
            	
            	
            	
            	mealExistsSuccessOrFail.setText("Please Enter Another Date");
            	
            	state = state.switchState(MealLoggingEvent.SUBMIT);
        		state.handle();
            	
            	ingredientsModel.clear();
        }
            else if (e.getSource() == search) {
            	
            	System.out.println((String) tfood.getEditor().getItem());
            	String[] searchFoodNames = dataStorage.searchFoodNames((String) tfood.getEditor().getItem());
            	
            	tfood.setModel(new DefaultComboBoxModel(searchFoodNames));
            
        }
    }
}

