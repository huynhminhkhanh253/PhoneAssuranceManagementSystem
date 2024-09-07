import React from 'react';
import './NavBar.css';
import {Link} from "react-router-dom";

function NavBar(props) {
    const data = props;
    return (
        <div className='navbar_component'>
            <Link to={'/'}>
                <img className='navbar_logo' src='https://ik.imagekit.io/cx85rn0p5/logo-rgb-rev.svg?updatedAt=1716446588037'></img>
            </Link>
            <div className='navbar_center'>
                <li>SUCCESS STORIES</li>
                <li>HOW IT WORKS</li>
                <li>NETWORKS AUCTIONS</li>
                <li>CONTACTS US</li>
            </div>
            <div className='navbar_right'>
                <Link style={{ textDecoration: "none" }} to={'/signup'}>
                    <div className='signup_button'>
                        SIGN UP
                    </div>
                </Link>
                <Link style={{ textDecoration: "none" }} to={'/login'}>
                    <div className='signin_button'>
                        CLIENT LOGIN
                    </div>
                    {/* <Button  type="button" class="btn btn-outline-warning">Warning</Button> */}
                </Link>
            </div>
        </div>
    );
}

export default NavBar;