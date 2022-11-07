//ROUGH example of how the API methods could be used to save a game

public GameSaverTool implements GameSaver{
public GameSaverTool(Model m){
            model  = m
        }
        //implement all the interface methods here
        public void saveGame(SaveSlot slot, SimFile newSim, CSVFile newCSV)

        }
CSVStates currentCSV = model.getCSVStates();
SimStates currentSim = model.getSIMStates();
File currentSim = makeSimFile(currentSim);
File currentCSV = makeSimFile(currentCSV);
Slot currentSlot = view.getSaveSlot();
saveGame(currentSlot, currentSim, currentCSV);
