import styled, { StyledComponent } from 'styled-components';
import { ITheme } from '../../styled/GlobalTheme';

interface NavBarProps {
    theme: ITheme;
}

export const StyledNavContent = styled.nav`
    max-width: ${({ theme }: NavBarProps) => theme.maxWidth};
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
`;
