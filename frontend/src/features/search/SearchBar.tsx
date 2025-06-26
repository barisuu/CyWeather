import { useState, useMemo, useEffect } from "react";
import {
    Box,
    TextField,
    Paper,
    List,
    ListItem,
    ListItemText,
    ClickAwayListener
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useCitySearch } from "../../hooks/useCitySearch";
import debounce from "lodash.debounce";

// Followed a tutorial for this component, don't really know the workings in depth, will learn more later.

export default function SearchBar() {
    const [search, setSearch] = useState("");
    const [debouncedSearch, setDebouncedSearch] = useState("");
    const [open, setOpen] = useState(false);
    const navigate = useNavigate();


    const debouncedSetSearch = useMemo(() =>
            debounce((value: string) => {
                setDebouncedSearch(value);
            }, 400),
        []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setSearch(value);
        debouncedSetSearch(value);
    };

    const { data: results = [] } = useCitySearch(debouncedSearch);

    useEffect(() => {
        if (debouncedSearch.length > 0) setOpen(true);
        else setOpen(false);
    }, [debouncedSearch]);

    return (
        <ClickAwayListener onClickAway={() => setOpen(false)}>
            <Box sx={{ position: "relative", width: 300, bgcolor:"white", borderRadius:5}}>
                <TextField
                    label="Search"
                    variant="outlined"
                    fullWidth
                    value={search}
                    onChange={handleChange}
                    onFocus={() => debouncedSearch && setOpen(true)}
                    autoComplete="off"
                />
                {open && results.length > 0 && (
                    <Paper
                        sx={{
                            position: "absolute",
                            top: "100%",
                            left: 0,
                            right: 0,
                            zIndex: 10,
                            maxHeight: 300,
                            overflowY: "auto",
                        }}
                    >
                        <List>
                            {results.map((city) => (
                                <ListItem
                                    key={city.id}
                                    onClick={() => {
                                        navigate(`/city/${city.id}`);
                                        setOpen(false);
                                        setSearch("");
                                        setDebouncedSearch("");
                                    }}
                                >
                                    <ListItemText
                                        primary={city.name}
                                        secondary={`${city.region}, ${city.country}`}
                                    />
                                </ListItem>
                            ))}
                        </List>
                    </Paper>
                )}
            </Box>
        </ClickAwayListener>
    );
}
