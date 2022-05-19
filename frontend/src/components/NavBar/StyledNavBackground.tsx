import styled from 'styled-components';
import { ITheme } from '../../styled/GlobalTheme';

interface NavBarProps {
    theme: ITheme;
}

const StyledNavBackground = styled.div`
    width: 100%;
    color: ${({ theme: { navBar } }: NavBarProps) => navBar.color};
    padding: 1rem
        ${({ theme: { globalXPadding } }: NavBarProps) => globalXPadding};
    background-color: ${(props: NavBarProps) =>
        props.theme.navBar.backgroundColor};
`;

export default StyledNavBackground;
