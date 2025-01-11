% Function to find user position in table

function row = searchName(data, first, last)
    row = 0;
    for r = 1:height(data)
        % Checks if given first name and last name match.
        if (strcmpi(data.Last_Name(r),last) && strcmpi(data.First_Name(r),first))
            row = r;
            break
        end
    end
end