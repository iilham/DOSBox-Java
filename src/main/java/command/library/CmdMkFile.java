/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */ 

package command.library;

import interfaces.IDrive;
import interfaces.IOutputter;
import command.framework.Command;
import filesystem.File;
import filesystem.FileSystemItem;
import java.util.ArrayList;

class CmdMkFile extends Command {

	public CmdMkFile(String cmdName, IDrive drive) {
		super(cmdName, drive);
	}

	@Override
	public void execute(IOutputter outputter) {
            String fileName = this.getParameterAt(0);
            String fileContent;
            if(this.getParameterCount() <= 1){
		fileContent = "";
            } else{
                fileContent = this.getParameterAt(1);
            }
            if (checkExists(fileName)){
                System.out.println("File has already exists !");
            } else {
                File newFile = new File(fileName, fileContent);
		this.getDrive().getCurrentDirectory().add(newFile);
            }
		
	}
        
        private boolean checkExists (String filename){
                ArrayList<FileSystemItem> fileExists = this.getDrive().getCurrentDirectory().getContent();
                boolean status = false;
                
                for(int i =0; i<fileExists.size(); i++){
                    if(!fileExists.get(i).getName().equals(filename) && status == false){
                        status = false;
                    } else{
                        status = true;
                    }
                }
                return status;
        }
}
