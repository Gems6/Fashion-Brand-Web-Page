% Function to find user matches.

function pref = matchUsers(data, pref, row)
    % Creates a sortable table to hold valid matches.
    matches = table('Size', [0 3], 'VariableTypes', {'double','string', 'string'}, ...
        'VariableNames', {'Points', 'LN', 'FN'});
    
    % Compares current user data and preferences
    % with the rest via a point system.
    r = 0;
    for r = 1:height(data)
        points = 0;
        % Only chooses users that share
        % language that are not the user themselves.
        if r ~= row && strcmpi(data.Language(row), data.Language(r))
            points = points + 2*(~strcmpi(data.Pet(row),'N/A') && strcmpi(data.Pet(row), pref.Pet(r)));
            points = points + (~strcmpi(pref.Pref_Gender(row),'N/A') && strcmpi(pref.Pref_Gender(row), data.Gender(r)));
            points = points + (~strcmpi(pref.Pref_Gender(r),'N/A') && strcmpi(pref.Pref_Gender(r), data.Gender(row)));
            points = points + (pref.Pref_Age(row) <= data.Age(r));
            % Rating affects points slightly to break point ties.
            % Only very high ratings affect the points significantly.
            points = points + (data.Rating(r) / 100.0);
            matches = [matches; {points, data.Last_Name(r), data.First_Name(r)}];
        end
    end
    % Sort rows by points descending.
    matches = sortrows(matches, -1);
    
    % Gives user list of matches to choose from.
    fprintf('Matches found: \n');
    for r = 1:height(matches)
        display(data(searchName(data, matches.FN(r), matches.LN(r)),:));
        if strcmpi(input('Would you like to match?(y/n):', "s"),'y')
            pref.matchFN(row) = {matches.FN(r)};
            pref.matchLN(row) = {matches.LN(r)};
            break % Exits loop once match is found.
        end
    end
    % Returns attempts to find match, and signals process completion.
    fprintf('%d attempts made.\n.', r);
    fprintf('Match found or end of list. Returning to menu.\n\n');
end