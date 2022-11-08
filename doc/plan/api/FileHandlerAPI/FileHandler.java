interface FileHandler
{
    //method that prompts user to choose a file of type fileExtension
    void uploadFile(FileChooser fileChooser, String fileExtension);

    //method that initializes the Map based on grid information from the .csv file
    Map loadMap(){}

    //checks the format of the file to ensure it's valid for parsing information.
    // otherwise, creates a GameError that will be displayed to user
    GameError checkFileFormat(){}



}