package game;

class Memento {
   private String undostate;

   public Memento(String stateToSave) { undostate = stateToSave; }
   public String getSavedState() { return undostate; }
   
   
}