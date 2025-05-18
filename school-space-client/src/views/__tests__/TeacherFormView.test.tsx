import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import TeacherFormView from '../TeacherFormView';

// On peut mocker le hook pour ne pas dépendre de sa logique interne
jest.mock('../../controller/useTeacherFormController', () => ({
    useTeacherFormController: () => ({
        teacher: { name: '', email: '', age: '' },
        handleChange: jest.fn(),
        handleSubmit: jest.fn((e) => e.preventDefault()),
    }),
}));

describe('TeacherFormView', () => {
    test('renders the form with inputs and submit button', () => {
        render(<TeacherFormView />);

        expect(screen.getByLabelText(/Nom/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Email/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Âge/i)).toBeInTheDocument();
        expect(screen.getByRole('button', { name: /Soumettre/i })).toBeInTheDocument();
    });

    test('can fill out and submit the form', () => {
        render(<TeacherFormView />);

        fireEvent.change(screen.getByLabelText(/Nom/i), { target: { value: 'Robert' } });
        fireEvent.change(screen.getByLabelText(/Email/i), { target: { value: 'robert@example.com' } });
        fireEvent.change(screen.getByLabelText(/Âge/i), { target: { value: '35' } });

        fireEvent.click(screen.getByRole('button', { name: /Soumettre/i }));

        // Ici tu pourrais ajouter des assertions supplémentaires si tu veux tester le comportement du mock
    });
});
