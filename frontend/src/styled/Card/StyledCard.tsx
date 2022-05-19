import styled from 'styled-components';
import { ITheme } from '../GlobalTheme';

interface CardProps {
    theme: ITheme;
}

export const StyledCard = styled.div`
    background-color: ${(props: CardProps) =>
        props.theme.cards.backgroundColor};

    margin: 1rem 0;
    padding: 1rem 0;
`;
