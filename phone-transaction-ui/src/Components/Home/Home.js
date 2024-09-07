import {React, useState} from 'react';
import './Home.css';
import { Link } from 'react-router-dom';


const Home = () => {
    const [order, setOrder] = useState([]);
    return (
        <div className='home_container'>
            <div className='blur_background'></div>
            <div className='vieworder_button'>
                <Link style={{textDecoration: "none"}} to={'/login'}>
                    View Order
                </Link>  
            </div>
        </div>
    );
};

export default Home;