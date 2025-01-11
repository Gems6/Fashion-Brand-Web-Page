% Function to edit data.

function [data, pref] = editData(data, pref, row)
    % Edits userdata elements.
    fprintf("Personal traits: \n");
    data.Age(row) = input("Enter age: ");
    data.Gender(row) = {input("Enter gender: (m/f)","s")};
    pet = {input("What pet do you need taken care of? (N/A if none): ", "s")};
    data.Pet(row) = pet;
    pref.Pet(row) = pet;
    data.Language(row) = {input("Enter your main language: ", "s")};
    
    % Edits user preferece elements.
    fprintf("\nPreferences: \n");
    pref.Pref_Age(row) = input("Minimum age of caretaker: ");
    pref.Pref_Gender(row) = {input("Preferred gender of caretaker (m/f/[N/A]): ", "s")};

end