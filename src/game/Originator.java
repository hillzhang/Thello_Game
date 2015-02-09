package game;
class Originator {
   private String undostate;
   private String redostate;

   public void set(String state) { 
       this.undostate = state; 
   }

   public Memento saveToMemento() { 
       return new Memento(undostate); 
   }
   public String restoreFromMemento(Memento m) {
       undostate = m.getSavedState(); 
       return undostate;
   }
   
   
   
   
   public void setRedo(String state) { 
       this.redostate = state; 
   }

   public RedoMemento saveToRedoMemento() { 
       return new RedoMemento(redostate); 
   }
   public String restoreFromRedoMemento(RedoMemento m) {
       redostate = m.getSavedState(); 
       return redostate;
   }
   
   
   
   
}   
