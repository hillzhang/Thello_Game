package game;

class RedoMemento {
   private String redostate;

   public RedoMemento(String stateToSave) { redostate = stateToSave; }
   public String getSavedState() { return redostate; }
   
   
}
