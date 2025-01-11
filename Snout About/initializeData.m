% Function to initialize data table files.

function initializeData()
    % Creates userdata table and elements.
    data = table('Size', [0 7], 'VariableTypes', {'string', 'string', 'double', 'string', 'string', 'string', 'double'}, ...
        'VariableNames', {'Last_Name', 'First_Name', 'Age', 'Gender', 'Pet', 'Language', 'Rating'});
    % Creates user preferences table and elements (includes password).
    pref = table('Size', [0 8], 'VariableTypes', {'string', 'string', 'string', 'double', 'string', 'string', 'string', 'string'}, ...
        'VariableNames', {'Last_Name', 'First_Name', 'Password', 'Pref_Age', 'Pref_Gender', 'Pet', 'matchLN', 'matchFN'});
    writetable(data, 'user_data.txt');
    writetable(pref, 'user_pref.txt');
end