#!/usr/bin/bash

# README
# PREREQUISITES: to use this script you must install the git-filter-repo python script
# Script can be found here: https://github.com/newren/git-filter-repo/blob/main/git-filter-repo
# Download the script and rename the file "git-filter-repo"
# Run command: git --exec-path
# Copy the script to this directory
# Make the script executable

# This script is intended to be used as part of the BitThink work flow.
# The script takes the last commit email on the current branch
# and assumes this author is the one to be removed and replaces this author
# with the specified new author.

# The script is intended to be run as a `pre-push` git hook and should be placed inside the
# .git/hooks path and made executable.

# This hook is called with the following parameters:

# $1 -- Name of the remote to which the push is being done
# $2 -- URL to which the push is being done

# Although only $1 is consumed by the script

# Exiting the script with 0 allows the push command by git to be completed.
# Exiting with any other value with stop the git push operation.

# All parameters below need to be configured EXCEPT:
# scope
# force
# destinationRemote

# It is also possible to hard code emails that you wish to be replaced by adding the emails to the "authors" array line 145

scope=""
force=""
newAuthorEmail="ayoolaadedeji@luno.com"
newAuthorName="ayo"
repositoryPath="/Users/ayoolaadedeji/Documents/LunoBT/android"
constrainChangesToCurrentBranch=true
forceCommand=true
scriptTriggerRemote="luno-remote"
destinationRemote="$1"

cd "$repositoryPath" || exit

if [ "$scriptTriggerRemote" != "$destinationRemote" ]; then
  echo "Pushing to remote other than *$scriptTriggerRemote*. Author rename will not be executed."
  exit 0
else
  echo "Pushing to *$scriptTriggerRemote*. Executing Author rename script."
fi

getCurrentBranch() {
  echo "$(git branch --show-current)"
}

if $constrainChangesToCurrentBranch; then
  scope="--refs refs/heads/$(getCurrentBranch)"
else
  scope=""
fi

if $forceCommand; then
  force="--force"
else
  force=""
fi

getLastCommitHash() {
  echo "$(git rev-parse --short HEAD)"
}

getAuthorFromCommit() {
  commitHash=$1
  echo "$(git show "$commitHash" | grep Author)"
}
#Deprecated
getAuthorEmail_() {
  string="$(getAuthorFromCommit "$(getLastCommitHash)")"
  startingIndex=$(indexOf "$string" "<")
  lastIndex="$(lastIndexOf "$string" ">")"
  substring="$(trim "$(expr substr "$string" "$startingIndex" "$lastIndex")")"
  echo "$substring" | sed 's|[<>,]||g'
}

getAuthorEmail() {
  gitAuthor=$(git var -l | grep -E '^GIT_AUTHOR_IDENT=' | sed -r 's/^[^=]+=//;s/( [^ ]+){2}$//')
  startingIndex=$(indexOf "$gitAuthor" "<")
  lastIndex="$(lastIndexOf "$gitAuthor" ">")"
  substring="$(trim "$(expr substr "$gitAuthor" "$startingIndex" "$lastIndex")")"
  echo "$substring" | sed 's|[<>,]||g'
}

getCommiterEmail() {
  gitCommiter=$(git var -l | grep -E '^GIT_COMMITTER_IDENT=' | sed -r 's/^[^=]+=//;s/( [^ ]+){2}$//')
  startingIndex=$(indexOf "$gitCommiter" "<")
  lastIndex="$(lastIndexOf "$gitCommiter" ">")"
  substring="$(trim "$(expr substr "$gitCommiter" "$startingIndex" "$lastIndex")")"
  echo "$substring" | sed 's|[<>,]||g'
}

#Takes 2 arguments $string $char then returns the index location of the first occurrence of the $char
indexOf() {
  index=0
  string=$1
  char=$2
  for ((i = 0; i < ${#string}; i++)); do
    if [ "${string:$i:1}" == "$char" ]; then
      index=$i
      break
    fi
  done
  echo "$index"
}

#Takes 2 arguments $string $char then returns the index location of the last occurrence of the $char
lastIndexOf() {
  index=0
  string=$1
  char=$2
  for ((i = ${#string}; i > 0; i--)); do
    if [ "${string:$i:1}" == "$char" ]; then
      index=$i
      break
    fi
  done
  echo "$index"
}

#takes one argument $string to be trimmed then returns trimmed string
trim() {
  local var="$*"
  var="${var#"${var%%[![:space:]]*}"}"
  var="${var%"${var##*[![:space:]]}"}"
  echo "$var"
}

#deleting replacements
deleteReplacements(){
for HID in $(git replace -l); do git replace -d $HID; done
}
#add additional emails here
authors=("$(getCommiterEmail)" "ayo.adedeji@bitthink.io" "harutyun.yesayan@bitthink.io" "yesayan.harut94@gmail.com" "nsikak@bitthink.io")

#--------------------------------------------------git filter-repo statement --------------------------------------
gitFilterRepoPrefix='
if commit.author_email == b'\""$(getAuthorEmail)"\"''

gitFilterRepoSuffix=:

gitFilterRepoBody='
    commit.author_email = b'\""$newAuthorEmail"\"'
    commit.author_name = b'\""$newAuthorName"\"'
    commit.committer_email = b'\""$newAuthorEmail"\"'
    commit.committer_name = b'\""$newAuthorName"\"''

getGitFilterCase() {
  echo ' or\
  commit.author_email == b'\""$1"\"' or\
  commit.committer_email == b'\""$1"\"''
}

gitFilterRepoStatement=$gitFilterRepoPrefix
#--------------------------------------------------git filter-repo statement --------------------------------------

for i in $(seq 0 ${#authors[@]}); do
  oldAuthor=${authors[$i]}
  if [[ "$oldAuthor" != "" ]]; then
    echo "Renaming *$oldAuthor* to *$newAuthorEmail*"
    gitFilterRepoStatement+=$(getGitFilterCase "$oldAuthor")
  fi
done
#constructing git filter-repo statement
gitFilterRepoStatement+=$gitFilterRepoSuffix$gitFilterRepoBody

deleteReplacements

#calling git filter repo
git filter-repo --commit-callback "$gitFilterRepoStatement" $scope $force

deleteReplacements

exit 0
