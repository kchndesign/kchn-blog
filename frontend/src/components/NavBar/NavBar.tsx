import React from 'react';
import { Link } from 'react-router-dom';
import StyledNavBackground from './StyledNavBackground';
import { StyledNavContent } from './StyledNavBar';

const NavBar: React.FC = () => {
    return (
        <StyledNavBackground>
            <StyledNavContent>
                <Link to="/">
                    <strong>Home</strong>
                </Link>
                <Link to="/article/new">
                    <strong>New Article</strong>
                </Link>
            </StyledNavContent>
        </StyledNavBackground>
    );
};

export default NavBar;
