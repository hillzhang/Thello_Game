package game;



class InterruptInput 
{
	
	UndoRedoCommandInterpret UR = new UndoRedoCommandInterpret();

  
  
  boolean isundoredo(String tmpstring){
	  
	  boolean flag = false;
	  
	  
	  
	  String[] rawindex = tmpstring.split(",");
	int moves = 0;
	  
	  try{
	   moves= Integer.parseInt(rawindex[1]);
	  }
	  catch (Exception e) {
		  flag= false;
	  }
	if(tmpstring.contains("undo")) {
		flag = true;
		
		UR.undo(moves);
		
		
	}
	
	
	else if (tmpstring.contains("redo")) {
		
		flag= true;

		 UR.redo(moves);
		 
		 
		
	}
	  
	  return flag;
  }
  
  
  
  
  
  void savetoUndolist(String coordinate) {
	  
	  UR.saveUndoState(coordinate);
	  
  }
  
  
  
  
	int ReadX(String theText)
	{
		return Integer.parseInt(theText.split(",")[0]);
	}
	
	int ReadY(String theText)
	{
		return Integer.parseInt(theText.split(",")[1]);
	}
  
  
  
}