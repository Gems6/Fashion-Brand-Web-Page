% Main Script for the Snout About app.
% Group B: Aaron Lianto, Omar Salheya, Sylvia Ezealor,
%          Edham Azraq, Mustafa Al Qasab

% Initializes user data and preferences files if not yet created.
if ~isfile('user_data.txt') || ~isfile('user_pref.txt')
    initializeData();
end

% Imports userdata and creates variables used.
data = readtable('user_data.txt');
pref = readtable('user_pref.txt');
option = '';
tempPass = '';
active = true;

% Requests name and searches for it in userdata.
fn = input("Enter your first name: ","s");
ln = input("Enter your last name: ","s");
row = searchName(data,fn,ln);
tempRow = 0;

% Prompts new users to create an account, and exits app if refused.
if (row == 0)
    if strcmpi(input("No profile found. Would you like to create? (y/n)","s"),'y')
        [data, pref] = addInfo(data,pref, fn, ln);
        row = searchName(data,fn,ln);
    else
        active = false;
    end
end

% Asks user for password until a correct one is given.
while ~strcmp(tempPass, pref.Password(row))
    tempPass = input('Enter password: ',"s");
    if strcmp(tempPass, pref.Password(row))
        fprintf('Logging in...\n');
    else
        fprintf('Incorrect password\n');
    end
end

% User will enter a letter to choose an option.
while(active)
    fprintf("Enter a letter to choose an option:\n");
    option = lower(input("(View profiles: 'V', Edit info: 'E', Edit password: 'P', Match: 'M',\nRate match: 'R', Delete profile: 'D', Quit: 'Q'\n)","s"));
    switch option    
        case 'm' % Matches users.
            pref = matchUsers(data, pref, row);
        case 'q' % Quits the program.
            active = false;
        case 'e' % Lets the user edit their data and preferences.
            [data, pref] = editData(data, pref, row);
        case 'r' % Lets the user rate their match if one exists.
            % Check's if user has a match.
            if (strcmpi(pref.matchFN(row),'N/A') && strcmpi(pref.matchLN(row),'N/A'))
                fprintf('No match found.\n');
            else
                tempRow = searchName(data,pref.matchFN(row), pref.matchLN(row));
                disp(data(tempRow,:)); % Displays the match if exists.
                option = lower(input("Would you like to add a like('l') or dislike('d')? Enter anything else to cancel.","s"));
                switch option
                    case 'l' % Adds to the match's rating.
                        fprintf('Liked\n');
                        data.Rating(tempRow) = data.Rating(tempRow) + 1;
                    case 'd' % Decreases the match's rating.
                        fprintf('Disliked\n');
                        data.Rating(tempRow) = data.Rating(tempRow) + -1;
                    otherwise % Any other entry cancels the rating.
                        fprintf('Cancelled\n');
                end
            end

        case 'p' % Lets user edit password.
            editPass(pref,row);
        case 'v' % Displays list of all users.
            disp(data);
        case 'd' % Asks if user wants to delete their account.
            if(strcmpi(input('Are you sure you want to delete (y/n)?',"s"),'y'))
                data(row,:) = [];
                pref(row,:) = [];
                fprintf('Goodbye');
                active = false;
            end
        otherwise % Prompts the user to enter a valid option.
            fprintf("Please enter valid option.\n\n");
    end
end

% Writes the new completed tables of userdata and preferences.
writetable(data, 'user_data.txt');
writetable(pref, 'user_pref.txt');