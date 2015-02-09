package game;
 
 class UndoRedoCommandInterpret {

	 
	private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();
    
   
    void undo(int moveback) {
   	 int sizeofstack= caretaker.getArraySize();
   	  sizeofstack = sizeofstack-1;
   	    if(moveback>sizeofstack){System.out.println("not enough moves to undo,try again!!");}
   	    else{
   	    	System.out.println("undo successfull");
   	     for(int i=0; i <moveback; i++){
   	    	
   	    	 originator.setRedo(originator.restoreFromMemento( caretaker.getMemento(sizeofstack-i)));
   	    	 caretaker.addRedoMemento(originator.saveToRedoMemento() );
   	    	 caretaker.undo(sizeofstack-i);
   	     }
   	     
   	  writeboardafterundoredo();
   	}}

	
	 void redo(int moveforward) {
		
	     int sizeofstack = 0;
	     sizeofstack = caretaker.getRedoArraySize();
	     if(moveforward>sizeofstack){System.out.println("not enough moves to redo,try again!!");}
	     else{
	    	 System.out.println("redo successfull");
	     for(int i=sizeofstack-1; i>=sizeofstack-moveforward;i-- ){
	    	 originator.set(originator.restoreFromRedoMemento( caretaker.getRedoMemento(i) ));
	         caretaker.addMemento( originator.saveToMemento() );
	         caretaker.redo(i);
	     }
	     
	    writeboardafterundoredo();
	     }
	}


 void saveUndoState(String state) {
	 originator.set(state);
     caretaker.addMemento( originator.saveToMemento() );
	
	
}


 int getArraysizeUndo(){
	
	
	return caretaker.getArraySize();
}

int getArraysizeRedo(){
	
	
	return caretaker.getRedoArraySize();
}



void writeboardafterundoredo(){
	  
	
	int size=caretaker.getArraySize()-1;
	
	
		 Board board=new Board();
		
	
	 String[] rawindex = originator.restoreFromMemento(caretaker.getMemento(size)).split(",");
	 for(int i=0; i<rawindex.length-1;i=i+3){
		 int row=Integer.parseInt(rawindex[i]);
		 int column=Integer.parseInt(rawindex[i+1]);
		 int piece=Integer.parseInt(rawindex[i+2]);
		
		 board.setPieceCell(row, column, piece);
		
		 
	 }
	 
	 int currentPlayer=Integer.parseInt(rawindex[rawindex.length-1]);
	 objController player=new objController();
	 player.setplayer(currentPlayer);
	 

	 board.DrawBoard();

	}




}




