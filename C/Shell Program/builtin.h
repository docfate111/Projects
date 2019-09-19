int sh_launch(char **args);
int sh_exec(char **args);
//built-in shell commands
int cd(char **args);
int help(char **args);
int sh_exit(char **args);
int ls(char **args);
char *builtin[]={"cd", "help", "exit", "ls"};
int num(){return sizeof(builtin)/sizeof(char*);}
int (*builtin_func[]) (char**)={&cd, &help, &sh_exit, &ls};
int sh_launch(char **args){
        pid_t pid, wpid;
        int status;
        pid=fork();
        if(pid==0){//child process begins
                if(execvp(args[0], args)==-1){printf("tsh? Error");}
                exit(0);
        }else if(pid<0){
                //error
                perror("tsh? Error");
        }else{//parent process
                do{
                        wpid=waitpid(pid, &status, WUNTRACED);
                }while(!WIFEXITED(status)&&!WIFSIGNALED(status));
        }
        return 1;
}
int sh_exec(char** args){
        if(args[0]==NULL){return 1;}//user entered nothing
        for(int i=0; i<num(); i++){
                if(strcmp(args[0], builtin[i])==0){
                //if builtin run builtin function
                        return (*builtin_func[i])(args);
                }
        }
        return sh_launch(args);
}
int cd(char *args[]){
        if(args[1]==NULL){//error if cd is entered without args
                fprintf(stderr, "tsh: expected arg to \"cd\"\n");
        }else{
                if(chdir(args[1])!=0){
                        perror("tsh");
                }
        }
        return 1;
}
int help(char **args){
        printf("ThelShel 1.0.0\n");
        printf("Built-in commands are:\n");
        for(int i=0; i<num(); i++){
                printf("%s\n", builtin[i]);
        }
        printf("Use man command for information\n");
	return 1;
}
int sh_exit(char **args){exit(0);}
int ls(char **args){
	//if(execvp("./ls", args)==-1){
	// 	perror("Make sure ls is compiled");
	//}
	system("./ls");
	return 1;
}
