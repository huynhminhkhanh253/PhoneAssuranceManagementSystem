import React, { useState } from 'react';
import SearchIcon from '@mui/icons-material/Search';
import './Search.css'

const SearchBar = ({ onSearch }) => {
    const [query, setQuery] = useState('');

    const handleInputChange = (e) => {
        setQuery(e.target.value);
    };

    const handleSearch = () => {
        onSearch(query);
    };

    return (
        <div className='search-bar'>
            <input
                className='search-input'
                type="text"
                value={query}
                onChange={handleInputChange}
                placeholder="Enter phone Imei"
            /> 
            <button className='search-button' onClick={handleSearch}>
                <SearchIcon/>
            </button>
            
        </div>
    );
};

export default SearchBar;