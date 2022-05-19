interface ICards {
    backgroundColor: string;
}

interface INavBar {
    backgroundColor: string;
    color: string;
}

export interface ITheme {
    globalXPadding: string;
    maxWidth: string;
    cards: ICards;
    navBar: INavBar;
}

export const theme: ITheme = {
    globalXPadding: '1rem',
    maxWidth: '67.5rem',
    cards: {
        backgroundColor: 'transparent',
    },
    navBar: {
        backgroundColor: '#252525',
        color: '#e9e9e9',
    },
};
