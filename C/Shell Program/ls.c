#include <stdio.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/stat.h>
void printdir(char* a);
char* permissions(char *file);
int main(int argc, char* argv[]){
	if(argc>1){
		printdir(argv[1]);
	}else{
		printdir(".");
	}
	return 0;
}
void printdir(char* a){
	struct dirent *de;
	DIR *dr=opendir(a);
	if(dr==NULL){
		printf("Could not open: ");
		puts(a);
		printf("\n Permissions error?");
	}
	while((de=readdir(dr))!=NULL){
		printf("%s     %s\n", de->d_name, permissions(de->d_name));
	}
	closedir(dr);
}
char* permissions(char *file){
    struct stat st;
    char *modeval = malloc(sizeof(char) * 9 + 1);
    if(stat(file, &st) == 0){
        mode_t perm = st.st_mode;
        modeval[0] = (perm & S_IRUSR) ? 'r' : '-';
        modeval[1] = (perm & S_IWUSR) ? 'w' : '-';
        modeval[2] = (perm & S_IXUSR) ? 'x' : '-';
        modeval[3] = (perm & S_IRGRP) ? 'r' : '-';
        modeval[4] = (perm & S_IWGRP) ? 'w' : '-';
        modeval[5] = (perm & S_IXGRP) ? 'x' : '-';
        modeval[6] = (perm & S_IROTH) ? 'r' : '-';
        modeval[7] = (perm & S_IWOTH) ? 'w' : '-';
        modeval[8] = (perm & S_IXOTH) ? 'x' : '-';
        modeval[9] = '\0';
        return modeval;
    }
    else{
        return "Error reading permissions";
    }
}
