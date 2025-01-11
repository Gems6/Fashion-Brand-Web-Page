% Function to edit user's password

function pref = editPass(pref, row)
    % Temporary variables to confirm password change.
    tempPass = 'no';
    temp2 ='C';

    % Prompts user to enter the correct password.
    while ~strcmp(tempPass, pref.Password(row))
    tempPass = input('Enter password (enter C to cancel): ',"s");
        if strcmp(tempPass, pref.Password(row)) % Confirms correct password. 
            fprintf('Confirmed\n');
        elseif strcmpi(tempPass, 'C') % Let's user cancel change.
            return;
        else % Reports if password was entered wrong.
            fprintf('Incorrect password\n');
        end
    end
    
    % Process to change password.
    while ~strcmp(tempPass,temp2)
        tempPass = input('Enter a password (C to cancel): ',"s");
        if strcmpi(tempPass,'c') % Cancels password change.
            return;
        end
        temp2 = input('Reenter password: ',"s"); % Confirms user password.
    end
    
    % Finalizes password update.
    pref.Password(row) = tempPass;

end