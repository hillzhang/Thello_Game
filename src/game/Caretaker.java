package game;

import java.util.ArrayList;
class Caretaker {
   private static ArrayList<Memento> undoList = new ArrayList<Memento>();
   private static ArrayList<RedoMemento> redoList = new ArrayList<RedoMemento>();

   public void addMemento(Memento m) { undoList.add(m); }
   
   public Memento getMemento(int index) { return undoList.get(index); }

    public void undo(int i){
    	
    	undoList.remove(i);
    	undoList.trimToSize();
    	
    }
    
    public int getArraySize(){
    	return undoList.size();
    	
    }

    
    public void addRedoMemento(RedoMemento m) { redoList.add(m); }
    public RedoMemento getRedoMemento(int index) { return redoList.get(index); }
    
    public int getRedoArraySize(){
    	return redoList.size();
    	
    }
    
public void redo(int i){
    	
    	redoList.remove(i);
    	redoList.trimToSize();
    	
    }
    
    
    
}   