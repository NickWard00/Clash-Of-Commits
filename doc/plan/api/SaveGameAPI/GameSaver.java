interface GameSaver
{
    //method that gets states for the CSV file such as:
    //current screen position, player position, leftover enemy positions
    CSVStates getCSVStates();

    //method that gets states for the SIM file such as:
    //player health, upgrades applied to player
    SimStates getSimStates();

    //method that makes a sim file out of SimStates
    makeSimFile(SimStates states);
    //likely need to try catch with badsimfile exception here in order to make a file

    //method that makes a CSV file out of CSVStates
    makeCSVFile(CSVStates csv);
    //likely need to try catch with badcsvfile exception here in order to make a file

    //writes new files to selected save slot (overwrites save if there are already files in that slot)
    saveGame(SaveSlot slot, SimFile newSim, CSVFile newCSV);

    //gets save slot (1,2,3) from view
    Slot getSaveSlot();
}
